package com.kierbob.fantasyarmor.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import com.kierbob.fantasyarmor.FantasyArmor;

/**
 * A closed, flat-topped great helm. It is a humanoid model where only the head has cubes,
 * so it can be posed like any other helmet. Texture: textures/entity/great_helm.png (64x64).
 */
public final class GreatHelmModel {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(FantasyArmor.id("great_helm"), "main");

	public static LayerDefinition createLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		PartDefinition head = root.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create()
				// the bucket: fits closely around the head, a little slimmer than a vanilla helmet
				.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F))
				// brass top plate, riveted around the edge, barely wider than the helm
				.texOffs(0, 16).addBox(-4.5F, -9.1F, -4.5F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.2F))
				// brass brow plate above the glowing V-shaped eye slit
				.texOffs(0, 27).addBox(-4.5F, -7.3F, -4.8F, 9.0F, 1.0F, 1.0F)
				// brass bar up the face, over the top and down the back (a cross with the brow)
				.texOffs(0, 29).addBox(-1.0F, -9.5F, -5.0F, 2.0F, 1.0F, 10.0F)
				.texOffs(48, 0).addBox(-1.0F, -9.4F, -4.9F, 2.0F, 10.0F, 1.0F)
				.texOffs(54, 0).addBox(-1.0F, -9.4F, 3.9F, 2.0F, 10.0F, 1.0F),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		// HumanoidModel expects the rest of the body to exist; leave those parts empty.
		head.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));
		root.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));
		root.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		root.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(mesh, 64, 64);
	}

	private GreatHelmModel() {
	}
}
